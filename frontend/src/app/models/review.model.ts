export interface Review {
  id?: number; 
  submittedBy: string;
  restaurantId: number;
  review: string;
  peanutScore?: number;
  eggScore?: number;
  dairyScore?: number;
  status?: 'PENDING' | 'ACCEPTED' | 'REJECTED';
}