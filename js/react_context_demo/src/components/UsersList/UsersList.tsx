import React, {ReactElement, useCallback, Profiler} from "react";
import User from "../../entities/User";
import {UserCard, MemoizedUserCard, ProfiledUserCard} from "../UserCard/UserCard";

interface IProps {
    users: User[] | null | undefined,
    selectedUser: User | null | undefined,
    onUserClick: (user: User) => any,
}

export const UsersList = (props: IProps): ReactElement => {

    const renderUsers = (): ReactElement[] | null => {
        if (!props.users || props.users?.length === 0) { return null };

        return props.users?.map((user: User) => {
           return (
                   <MemoizedUserCard
                       key={user.id}
                       user={user}
                       onClick={onCardClick}
                       isSelected={props.selectedUser?.id === user?.id}
                       randomData={randomDataCb()}
                   />
           );
        });
    };

    const onCardClick = useCallback( (user: User) => { props.onUserClick(user) }, [])

    const randomDataCb = useCallback(() => {
        return {
            date: new Date(),
            userDiscount: Math.random() * 99 + 1,
        }
    }, [])

    return (
        <div className="user-list__container">
            {
                renderUsers()
            }
        </div>
    );
}

export const MemoizedUsersList = React.memo(UsersList, (prevProps, nextProps) => {
    return prevProps.users?.length === nextProps.users?.length;
});
