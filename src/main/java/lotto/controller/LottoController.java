package lotto.controller;

import java.util.HashMap;
import lotto.service.AdminService;
import lotto.type.StepType;
import lotto.service.UserService;
import lotto.service.LogInService;
import lotto.view.LottoBundleView;
import lotto.view.LottoResultView;
import utils.Logger;

/**
 * 컨트롤러는 DTO를 조작하고, 실행 단계에 따라 필요한 서비스를 불러옵니다.
 */
public class LottoController {

    private StepType currentStep;
    private final LogInService logInService;
    private final UserService userService;
    private final AdminService adminService;
    private final LottoBundleView lottoBundleView;
    private final LottoResultView lottoResultView;
    private final HashMap<StepType, Runnable> stepMapper;
    private Long userId;
    private LottoController() {
        currentStep = StepType.INIT;
        logInService = new LogInService();
        userService = new UserService();
        adminService = new AdminService();
        lottoBundleView = new LottoBundleView();
        lottoResultView = new LottoResultView();
        stepMapper = mapStepTypes();
    }

    private HashMap<StepType, Runnable> mapStepTypes() {
        HashMap<StepType, Runnable> stepMapper = new HashMap<>();
        stepMapper.put(StepType.INIT, this::init);
        stepMapper.put(StepType.BUY_LOTTO, this::buyLotto);
        stepMapper.put(StepType.DECIDE_WINNER, this::decideWinner);
        stepMapper.put(StepType.DECIDE_BONUS, this::decideBonus);
        stepMapper.put(StepType.GIVE_RESULT, this::giveResult);
        return stepMapper;
    }

    private void init() {
    }

    private void buyLotto() {
    }

    private void decideWinner() {
    }

    private void decideBonus() {
    }

    private void giveResult() {
    }

    public static void run() {
        LottoController lottoController = new LottoController();
        try {
            lottoController.manipulate();
        } catch (Exception exception) {
            Logger.log(exception.getMessage());
        }
    }

    private void manipulate() {
        while(currentStep != StepType.FINISH) {
            stepMapper.get(currentStep).run();
        }
    }
}
