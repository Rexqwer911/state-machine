package com.rexqwer.statemachinedemoapp.machine.enums;

public enum OrderState {
    NEW,
    START,
    MIDDLE,
    FORK,
    FORK_FIRST_START,
    FORK_FIRST_FINISH,
    FORK_SECOND_START,
    FORK_SECOND_FINISH,
    JOIN,
    PRE_FINISH,
    FINISH
}
