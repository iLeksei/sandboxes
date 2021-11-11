import React, {ReactElement, useEffect, Profiler} from "react";
import User from "../../entities/User";

import "./UserCard.css";

interface IProps {
    user: User,
    onClick: (user: User) => any,
    isSelected: boolean,
    randomData: any,
}

export const UserCard = (props: IProps): ReactElement => {
    const unknown: string = "unknown";
    const containerStyles = props.isSelected ?
        "user-card__container user-card__container-selected" : "user-card__container";

    const onCardClick = () => {
        props.onClick(props.user);
    }

    return (
        // @ts-ignore
        <div className={containerStyles} onClick={onCardClick}>
            <div className="user-card__idx">{props.user?.id || ""}</div>
            <div className="user-card__name">{props.user?.name || unknown}</div>
            <div className="user-card__username">{props.user?.username || unknown}</div>
        </div>
    );
}

/**
 *  Re-render will be only if prop isSelected is changed.
 *  otherwise our prop randomData force re-render
 */
export const MemoizedUserCard = React.memo(UserCard , ((prevProps, nextProps) => {
    return prevProps.isSelected === nextProps.isSelected;
}));

export const ProfiledUserCard = (props: any) => {
   return (
       <Profiler
            id="user-card-profiler"
            onRender={console.log}
        >
            <UserCard  {...props} />
        </Profiler>
   )
}