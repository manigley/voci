package ch.bytecrowd.voci.interfaces;

import javax.inject.Qualifier;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;

@Retention(RUNTIME)
@Qualifier
public @interface Scopeless {

}
