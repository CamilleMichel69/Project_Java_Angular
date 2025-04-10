export interface Restaurant {
    id?: number;
    name: string;
    address: string;
    zipCode: string;
    city: string;
    peanutScore?: number;
    eggScore?: number;
    dairyScore?: number;
}