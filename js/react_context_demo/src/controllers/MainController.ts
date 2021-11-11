import Post from "../entities/User";

export class MainController {

    private static MAIN_URL: Request | string = "https://jsonplaceholder.typicode.com/";

    public static fetchUsers = async (): Promise<Post | null> => {
        try {
            const response: Response = await fetch(this.MAIN_URL + "users");
            return response.json();
        } catch (e) {
            console.error("Error with getting data");
            return null;
        }
    }


}