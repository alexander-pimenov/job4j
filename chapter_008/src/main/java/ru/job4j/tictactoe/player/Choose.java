package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.input.Input;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.model.IBoard;

public interface Choose {

    Player[] getPlayers(Input input, IBoard board, Logic logic);
}