/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.error.Mark;

/**
 * @see PyYAML 3.06 for more information
 */
public class BlockMappingStartToken extends Token {

    public BlockMappingStartToken(Mark startMark, Mark endMark) {
        super(startMark, endMark);
    }

    @Override
    public String getTokenId() {
        return "<block mapping start>";
    }
}
