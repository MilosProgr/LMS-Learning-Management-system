export interface NavItem {
    label: string;
    link?: string; // link je opcionalan
    isButton?: boolean; // da li je dugme
    action?: () => void; // funkcija koja se poziva kada se dugme pritisne
    submenu?: NavItem[]; // opcioni podmeni
    isVisible?: boolean; // da li se stavka prikazuje
  }
  
  
  