import React, {useState, useEffect, useRef} from "react";

import "./Timer.scss";

export const Timer = (props) => {
    const [timer, setTimer] = useState(0);
    let timerRef = useRef(false);

    useEffect(() => {
        let timerId = setTimeout(() => {
            setTimer(timer => timer + 1)
        }, 1000);
        return () => clearInterval(timerId);
    }, [timer]);

    const startBtnHandler = () => {

    }

    const stopBtnHandler = () => {

    }

    const resetBtnHandler = () => {

    }


    return (
        <div className="timer__wrapper">
            <div className="timer__counter-container">{timer}</div>
            <div className="timer__button-container">
                <button id="start-btn">start</button>
                <button id="stop-btn">stop</button>
                <button id="reset-btn">reset</button>
            </div>
        </div>
    )
}
