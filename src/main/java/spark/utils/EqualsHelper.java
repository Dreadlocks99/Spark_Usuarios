package spark.utils;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.TagType;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.IOException;

public class EqualsHelper implements Helper {
    @Override
    public Object apply(Object o, Options options) throws IOException {
        Object b = options.param(0, null);
        //o = 1
        //b = 1
        boolean result = new EqualsBuilder().append(o, b).isEquals();

        System.out.println(o.toString());
        System.out.println(b.toString());

        if(options.tagType == TagType.SECTION){
            return result ? options.fn() : options.inverse();
        }
        return result? options.hash("yes", "true")
                : options.hash("no", false);
    }
}
