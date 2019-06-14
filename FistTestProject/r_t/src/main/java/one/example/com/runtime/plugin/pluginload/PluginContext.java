package one.example.com.runtime.plugin.pluginload;

import android.content.Context;
import android.content.ContextWrapper;

public class PluginContext extends ContextWrapper {
    public PluginContext(Context base) {
        super(base);
    }
}
