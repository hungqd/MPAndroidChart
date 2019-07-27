package com.hungqd.charting.renderer;

import android.graphics.Path;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class YAxisRendererFullWidth extends YAxisRenderer {

    public YAxisRendererFullWidth(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer trans) {
        super(viewPortHandler, yAxis, trans);
    }

    @Override
    protected Path linePath(Path p, int i, float[] positions) {
        p.moveTo(0, positions[i + 1]);
        p.lineTo(mViewPortHandler.contentRight(), positions[i + 1]);
        return p;
    }
}
