import {Address} from "./Address";
import {Company} from "./Company";

export default interface User {
    "id": number,
    "name": string,
    "username": string,
    "email": string,
    "address": Address,
    "phone": string,
    "website": string,
    "company": Company
}