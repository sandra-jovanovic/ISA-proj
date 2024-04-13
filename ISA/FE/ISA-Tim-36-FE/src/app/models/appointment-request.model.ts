import BloodTypesWithQuantity from "./bloodTypesWithQuantity.model";
import EquipmentWithQuantity from "./equipmentWithQuantity.model";

export default interface AppointmentRequest {
  id: string;
  didNotShow: boolean;
  conditionsNotFulfilled: boolean;
  spentEquipment: EquipmentWithQuantity[];
  spentBloodTypes : BloodTypesWithQuantity[];
  details: string;
}
