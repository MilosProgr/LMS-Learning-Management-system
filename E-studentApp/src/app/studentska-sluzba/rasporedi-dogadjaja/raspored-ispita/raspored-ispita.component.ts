import { Component, inject } from '@angular/core';
import { IspitniRok } from '../../../../models/ispitniRok/ispitniRokModel';
import { IspitniRokService } from '../../../../Services/ispitniRok/ispitni-rok.service';
import { CommonModule } from '@angular/common';
import { CalendarEvent, CalendarModule, CalendarMonthViewBeforeRenderEvent, CalendarView, CalendarWeekViewBeforeRenderEvent } from 'angular-calendar';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-raspored-ispita',
  standalone: true,
  imports: [CommonModule, CalendarModule, MatTooltipModule],
  templateUrl: './raspored-ispita.component.html',
  styleUrls: ['./raspored-ispita.component.css']
})
export class RasporedIspitaComponent {
   private service = inject(IspitniRokService);

  view: CalendarView | 'month' | 'week' | 'day' = CalendarView.Month;
  viewDate = new Date();

  events: CalendarEvent[] = [];

  ngOnInit(): void {
    this.ucitajRokove();
  }

  // Učitavanje ispitnih rokova i mapiraj u CalendarEvent sa meta: { rok }
  private ucitajRokove(): void {
    this.service.getAll().subscribe({
      next: (rokovi: IspitniRok[] = []) => {
        this.events = rokovi.map(r => this.rokToEvent(r));
        console.log(rokovi);
        
      },
      error: (err) => console.error('Greška pri učitavanju rokova:', err)
    });
  }

  private rokToEvent(r: IspitniRok): CalendarEvent {
    const start = this.toDate(r.pocetak);
    const end   = this.toDate(r.kraj);
    console.log(r.redovan);
    

    const color = r.redovan
      ? { primary: '#1976d2', secondary: '#E3F2FD' }   // plava
      : { primary: '#d32f2f', secondary: '#FFEBEE' };  // crvena

    return {
      id: r.id,
      title: r.naziv + (r.redovan ? ' (redovan)' : ' (vanredni)'),
      start,
      end,
      color,
      allDay: false,
      meta: { rok: r }   // 🔑 koristimo kasnije u beforeViewRender
    };
  }

  private toDate(val: string | number[] | Date | null | undefined): Date {
    if (!val) return new Date();
    if (val instanceof Date) return val;
    if (typeof val === 'string') return new Date(val);
    if (Array.isArray(val)) {
      const [y, m, d, hh = 0, mm = 0, ss = 0] = val;
      return new Date(y, (m ?? 1) - 1, d ?? 1, hh, mm, ss);
    }
    return new Date(val as any);
  }

  // --- Oboj dan koji ima bar jedan rok ---
  onBeforeMonthViewRender(ev: any /* CalendarMonthViewBeforeRenderEvent */) {
    ev.body.forEach((day: any) => {
      const cover = (day.events ?? []) as any[];
      if (!cover.length) return;
      const hasRedovan = cover.some(e => e?.event?.meta?.rok?.redovan === true);
      day.cssClass = hasRedovan ? 'rok-day--redovan' : 'rok-day--vanredni';
    });
  }

  onBeforeWeekViewRender(ev: any /* CalendarWeekViewBeforeRenderEvent */) {
    ev.header.forEach((header: any) => {
      const dayEvents = (ev.period.events ?? []).filter((e: any) =>
        header.date >= (e.start as Date) && header.date < (e.end as Date)
      );
      if (!dayEvents.length) return;
      const hasRedovan = dayEvents.some((e: any) => e?.meta?.rok?.redovan === true);
      header.cssClass = hasRedovan ? 'rok-day--redovan' : 'rok-day--vanredni';
    });
  }

  // Toolbar kontrole
  setView(v: 'month' | 'week' | 'day') { this.view = v as any; }
  today() { this.viewDate = new Date(); }
  prev()  { this.shiftView(-1); }
  next()  { this.shiftView(1); }

  private shiftView(dir: 1 | -1) {
    const d = new Date(this.viewDate);
    if (this.view === 'month') d.setMonth(d.getMonth() + dir);
    else if (this.view === 'week') d.setDate(d.getDate() + 7 * dir);
    else d.setDate(d.getDate() + dir);
    this.viewDate = d;
  }

  getDayTooltip(day: any): string {
  const events = (day?.events ?? []) as any[];
  const titles = events
    .map(e => e?.title ?? e?.event?.title) // radi i za MonthView/WeekView oblike
    .filter((t: any) => !!t);
  return titles.join(', ');
}

}
