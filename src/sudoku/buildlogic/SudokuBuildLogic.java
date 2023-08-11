package sudoku.buildlogic;

import sudoku.computationlogic.GameLogic;
import sudoku.constants.GameState;
import sudoku.persistence.LocalStorageImpl;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userInterface.IUserInterfaceContract;
import sudoku.userInterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameDate(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic
                = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
