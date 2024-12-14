package com.matthieu.aoc.resolver.year_2024;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver13p1 implements Resolver {

    protected static final int scale = 20;

    protected List<ClawConfig> clawConfigs;
    protected long tokens;

    protected record ClawConfig(Duo<Long, Long> a, Duo<Long, Long> b, Duo<Long, Long> prize) {}

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.initliazeClawConfigs(values, 0);
    }

    @Override
    public boolean solve() throws Exception {

        clawConfigs.forEach(claw -> {
            BigDecimal b = divide(BigDecimal.valueOf(claw.b.y()), BigDecimal.valueOf(claw.b.x()))
                    .multiply(BigDecimal.valueOf(claw.prize.x()))
                    .subtract(BigDecimal.valueOf(claw.prize.y()));

            BigDecimal commonDenominator = BigDecimal.valueOf(claw.b.x() * claw.a.x());
            BigDecimal commonNominator = BigDecimal.valueOf(claw.b.y() * claw.a.x() - claw.a.y() * claw.b.x());
            BigDecimal x = b.multiply(divide(commonDenominator, commonNominator));
            double xCorner = x.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();

            double nbButtonA = xCorner / claw.a.x();
            double nbButtonB = (claw.prize.x() - xCorner) / claw.b.x();

            if (nbButtonA % 1 == 0.0 && nbButtonB % 1 == 0.0) {
                tokens += nbButtonA * 3l + nbButtonB;
            }
        });

        return true;
    }

    protected void initliazeClawConfigs(List<String> values, long factor) {
        this.clawConfigs = new ArrayList<>();

        for (int i = 0; i < values.size(); i += 4) {
            String[] A = values.get(i).replaceAll("Button A: X+", "").split(", Y+");
            String[] B = values.get(i + 1).replaceAll("Button B: X+", "").split(", Y+");
            String[] prize = values.get(i + 2).replaceAll("Prize: X=", "").split(", Y=");

            this.clawConfigs.add(new ClawConfig(
                    new Duo<>(Long.parseLong(A[0]), Long.parseLong(A[1])),
                    new Duo<>(Long.parseLong(B[0]), Long.parseLong(B[1])),
                    new Duo<>(Long.parseLong(prize[0]) + factor, Long.parseLong(prize[1]) + factor)));
        }
    }

    public static void main(String[] args) {
        // Button A: X+17, Y+86
        // Button B: X+84, Y+37
        // Prize: X=7870, Y=6450

        // y = (37/84)x + b
        // -b = (37/84)x -y
        // -b = (37/84)*7870 - 6450
        // -b = -2983.45 ...

        // -b = (37/84)x - (86/17)x

        // Find b
        BigDecimal b = divide(BigDecimal.valueOf(37), BigDecimal.valueOf(84)).multiply(BigDecimal.valueOf(7870))
                .subtract(BigDecimal.valueOf(6450));
        
        System.out.println(b);
        
        // Armoniser les fractions
        long commonDenominator = 84 * 17;
        long newAY = 86 * 84;
        long newBY = 37 * 17;
        long commonNominator = newBY - newAY;
        

        BigDecimal x = b.multiply(divide(BigDecimal.valueOf(commonDenominator), BigDecimal.valueOf(commonNominator)));
        int xCorner = x.round(MathContext.DECIMAL32).intValue();

        double nbButtonA = xCorner / 17;
        // Check nbButtonA integrity

        double nbButtonB = (7870 - xCorner) / 84;

        System.out.println(nbButtonB);
    }

    protected static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b, scale, RoundingMode.HALF_DOWN);
    }

    @Override
    public String get() {
        return tokens + "";
    }

    protected long minTokenToWin(ClawConfig clawConfig) {

        return 0;
    }
}
