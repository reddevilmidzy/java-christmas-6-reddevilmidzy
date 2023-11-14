package christmas.mock;

import christmas.view.InputViewImpl;

import java.util.Arrays;
import java.util.Iterator;

public class MockInputView extends InputViewImpl {

    public final Iterator<String> mockInputs;

    public MockInputView(String... mockInputs) {
        this.mockInputs = Arrays.stream(mockInputs).iterator();
    }

    @Override
    protected String readLine() {
        return mockInputs.next();
    }
}
