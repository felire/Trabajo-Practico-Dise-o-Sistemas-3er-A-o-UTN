package ar.utn.frba.disenio.tp_anual;

import java.util.Comparator;

import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Sorter;

public class DescriptionSorterRunner extends JUnit4ClassRunner {

    private static final Comparator<Description> comparator = new Comparator<Description>() {

        public int compare(Description o1, Description o2) {
            return o1.getDisplayName().compareTo(o2.getDisplayName());
        }
    };

    public DescriptionSorterRunner(Class<?> klass) throws InitializationError {
        super(klass);
        sort(new Sorter(comparator));
}
}