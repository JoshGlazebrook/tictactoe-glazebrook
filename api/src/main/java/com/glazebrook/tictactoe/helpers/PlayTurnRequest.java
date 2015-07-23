package com.glazebrook.tictactoe.helpers;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PlayTurnRequest {

    @NotNull
    private UUID playerId;

    @NotNull
    @Min(0)
    @Max(2)
    private int row;

    @NotNull
    @Min(0)
    @Max(2)
    private int col;

    public PlayTurnRequest() {

    }

    public PlayTurnRequest(UUID playerId, int row, int col) {
        this.playerId = playerId;
        this.row = row;
        this.col = col;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
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
