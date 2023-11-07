package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.Rank.*;

public class LottoGame {

    final static int START_NUMBER = 1;
    final static int END_NUMBER = 45;
    final static int COUNT_NUMBER = 6;
    static int lottoCnt;
    public static int issuanceLotto(int money){
        lottoCnt = money/1000;
        return lottoCnt;
    }

    public static List<List<Integer>> getLottoBundle(){
        List<List<Integer>> tickets = new ArrayList<>();
        for (int i = 0; i < lottoCnt; i++) {
            tickets.add(getRandomNumbers());
        }
        Output.checkLottoNumberMessage(tickets);
        return tickets;
    }



    public static List<Integer> getRandomNumbers(){
        List<Integer> randomNumbers = new ArrayList<> (Randoms.pickUniqueNumbersInRange(START_NUMBER,END_NUMBER,COUNT_NUMBER));
        Collections.sort(randomNumbers);
        return randomNumbers;
    }

    public static List<Integer> MakeWinningNumbers(String winningNumbers) {
        List<Integer> winningNumbersList = new ArrayList<>();
        String[] splitNumbers = winningNumbers.split(",");

        for (int i = 0; i < Input.LOTTO_WINNING_NUMBER_COUNT; i++) {
            winningNumbersList.add(Integer.parseInt(splitNumbers[i]));
        }

        return winningNumbersList;
    }

    public static void getTwoNumbers(List<Integer> winningNumbers, List<List<Integer>> userNumbersBundle, int bonus){
        int[] equalNumCount = new int[2];
        for (List<Integer> userNumbersUnit : userNumbersBundle) {
            equalNumCount = compareNumbers(winningNumbers, userNumbersUnit, bonus);
            result(equalNumCount);
        }
    }

    public static int[] compareNumbers(List<Integer> winningNumbers, List<Integer> userNumberUnit, int bonus){
        int cnt = 0;
        int bonusCnt = 0;
        int[] cntFile = new int[2];
        for (int winningNumber : winningNumbers) {
            if (userNumberUnit.contains(winningNumber)) {
                cnt++;
            }
        }
        if(cnt == 5) {
            if (userNumberUnit.contains(bonus)){
                bonusCnt++;
            }
        }
        cntFile[0] = cnt;
        cntFile[1] = bonusCnt;
        //System.out.println(cntFile[0]+","+cntFile[1]);
        return cntFile;
    }

    public static void result(int[] equalNumCount){

        if (equalNumCount[0] == 3){
            FIFTH.setCnt();
        }
        else if(equalNumCount[0] == 4){
            FOURTH.setCnt();
        }
        else if(equalNumCount[0] == 5){
            if(equalNumCount[1] == 0){
                THIRD.setCnt();
            }
            else if (equalNumCount[1] == 1){
                SECOND.setCnt();
            }
        }
        else if (equalNumCount[0] == 6){
            FIRST.setCnt();
        }
    }
}
