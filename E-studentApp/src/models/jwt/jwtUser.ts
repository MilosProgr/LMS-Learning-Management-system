export interface JwtUser {
    id?: number;
    sub?: string;
    roles: string[];
}