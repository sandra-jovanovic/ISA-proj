import { Centre } from "./centre.model";
import { User } from "./user.model";

export default interface Appointment {
  id: string;
  medicalWorker: User;
  dateTime: Date;
  durationInMinutes: number;
  info: string;
  user: User;
  centre: Centre;
}
