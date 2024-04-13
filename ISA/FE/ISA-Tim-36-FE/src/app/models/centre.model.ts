class CentrePost {
  id?: any;
  name!: string;
  address!: string;
  number!: string;
  averageRate!: number;
  description!: string;
  adminId!: number;
  reserveAppointment?:string;

}

class Centre {
  id?: any;
  averageRate?: number;
  description?: string;
  name?: string;
  streetName?: string;
  streetNumber?:string;
  reserveAppointment?:string;
  administrator?:number ;
  
  
}

export {CentrePost, Centre}
