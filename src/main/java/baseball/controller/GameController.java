package baseball.controller;

import baseball.model.AnswerNumber;
import baseball.model.InputNumber;
import baseball.view.InputView;
import baseball.view.OutputView;

public class GameController {
    static final int NUMBER_OF_DIGITS = 3;
    static final AnswerNumber ANSWER_NUMBER = new AnswerNumber();
    private CountController countController = new CountController();
    private OutputView outputView = new OutputView();
    private InputView inputView = new InputView();

    public void createAnswer() {
        ANSWER_NUMBER.createRandomNumberList();
    }

    public void startGame() {
        System.out.println(outputView.printStart());
        proceedGame();
    }

    public void proceedGame() {
        createAnswer();
        InputNumber inputNumber;

        do {
            inputNumber = new InputNumber();
            inputNumber.setInputNumber(inputView.getPlayerInput());

            String hintMessage = outputView.printHint(countController.getHintMessage(inputNumber));
            System.out.println(hintMessage);

        } while (!isDone(inputNumber));

        inputNumber.setQuitNumber(inputView.getRestartOrQuitInput());

        if (!isQuit(inputNumber)) {
            proceedGame();
        }

    }

    public boolean isDone(InputNumber inputNumber) {
        if (countController.getStrikeCount(inputNumber) == NUMBER_OF_DIGITS) {
            System.out.println(outputView.printSuccess());
            return true;
        }
        return false;
    }

    public boolean isQuit(InputNumber inputNumber) {
        if (inputNumber.getQuitNumber().equals("2")) {
            return true;
        }
        return false;
    }
}