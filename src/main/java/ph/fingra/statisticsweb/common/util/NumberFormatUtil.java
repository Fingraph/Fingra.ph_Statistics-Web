/**
 * Copyright 2014 tgrape Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ph.fingra.statisticsweb.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public abstract class NumberFormatUtil {
    
    public static BigDecimal longDouble2BigDeciaml(int size, double value) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(size);
        nf.setGroupingUsed(false);
        return new BigDecimal(nf.format(value));
    }
    
    private static final String symbols = " kMGTPEZY";
    
    public static String shortScaleConvertWithBigDecimal(BigDecimal num) {
        String formatted = NumberFormat.getNumberInstance().format(
                num.setScale(1, RoundingMode.HALF_UP).doubleValue());
        String[] vals = formatted.split(",");
        int idx = vals.length;
        if (idx == 1)
            return formatted;
        return vals[0] + ((vals[1].charAt(0) == '0') ?
                symbols.charAt(idx - 1) : ("." + (vals[1].charAt(0)) + symbols.charAt(idx - 1)));
    }
    
}
