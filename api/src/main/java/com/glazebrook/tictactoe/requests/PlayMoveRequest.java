package com.glazebrook.tictactoe.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PlayMoveRequest {

    @NotNull
    private UUID token;

    @NotNull
    @Min(0)
    @Max(2)
    private int row;

    @NotNull
    @Min(0)
    @Max(2)
    private int col;

    public PlayMoveRequest() {

    }

    public PlayMoveRequest(UUID token, int row, int col) {
        this.token = token;
        this.row = row;
        this.col = col;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
