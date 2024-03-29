import React, { ReactElement, useState, useRef, useCallback, createContext } from "react";
import Post from "../entities/User";
import Action from "../entities/Action";

export const UsersContext = createContext<{users: Post[] | null, selectedUser: Post | null} | null>(null);
export const UsersDispatchContext = createContext<any>(null);

/**
 *  this is simple example how we can use React context instead of redux
 */
export const UsersContextComponent = ({ children }: any): ReactElement => {
    const [state, setState] = useState<{users: Post[] | null, selectedUser: Post | null} | null>(null);
    // const [posts, setPosts] = useState<Post[] | null>(null);
    // const [selectedPost, setSelectedPosts] = useState<Post | null>(null);
    const dispatchRef = useRef({});

    dispatchRef.current = (action: Action) => {
        console.log(action)
        switch (action.type) {
            case "SET_USERS":
                // setPosts(action.payload);
                setState((state: any) => ({ ...state, users: action.payload, }));
                break;
            case "SELECT_USER":
                // setSelectedPosts(action.payload);
                setState((state: any) => ({ users: state.users, selectedUser: action.payload,  }));
                break;
            case "RESET_STORE":
                setState({ users: [], selectedUser: null });
                break;
            default: break;
        }
    }

    const dispatch = useCallback(
        // @ts-ignore
        (action: Action) => dispatchRef.current?.(action) , [])

    return (
        <UsersContext.Provider value={state}>
            <UsersDispatchContext.Provider value={dispatch}>
                { children }
            </UsersDispatchContext.Provider>
        </UsersContext.Provider>
    );
}
