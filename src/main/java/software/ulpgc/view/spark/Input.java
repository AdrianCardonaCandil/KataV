package software.ulpgc.view.spark;

import software.ulpgc.model.Currency;
import software.ulpgc.model.Money;

public interface Input {
    public abstract Money getMoney();
    public abstract Currency getCurrency();
}
