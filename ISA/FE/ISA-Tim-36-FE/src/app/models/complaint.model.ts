export default interface Complaint {
    id: number;
    centerId: number;
    centerName: string;
    medicalWorkerId: number;
    medicalWorkerFirstName: string;
    medicalWorkerLastName: string;
    userId: number;
    userFirstName: string;
    userLastName: string;
    description: string;
    answer: string;
}
