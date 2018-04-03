/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.a;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.continuous.BecomesCreatureSourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.mana.BlueManaAbility;
import mage.abilities.mana.WhiteManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.game.permanent.token.TokenImpl;import mage.game.permanent.token.Token;

/**
 * @author LevelX2
 */
public class AzoriusKeyrune extends CardImpl {

    public AzoriusKeyrune(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{3}");

        // {T}: Add {W} or {U} to your mana pool.
        this.addAbility(new WhiteManaAbility());
        this.addAbility(new BlueManaAbility());

        // {W}{U}: Azorius Keyrune becomes a 2/2 white and blue Bird artifact creature with flying until end of turn.
        this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, new BecomesCreatureSourceEffect(new AzoriusKeyruneToken(), "", Duration.EndOfTurn), new ManaCostsImpl("{W}{U}")));
    }

    public AzoriusKeyrune(final AzoriusKeyrune card) {
        super(card);
    }

    @Override
    public AzoriusKeyrune copy() {
        return new AzoriusKeyrune(this);
    }

    private static class AzoriusKeyruneToken extends TokenImpl {
        AzoriusKeyruneToken() {
            super("", "2/2 white and blue Bird artifact creature with flying");
            cardType.add(CardType.ARTIFACT);
            cardType.add(CardType.CREATURE);
            color.setWhite(true);
            color.setBlue(true);
            this.subtype.add(SubType.BIRD);
            power = new MageInt(2);
            toughness = new MageInt(2);
            this.addAbility(FlyingAbility.getInstance());
        }
        public AzoriusKeyruneToken(final AzoriusKeyruneToken token) {
            super(token);
        }

        public AzoriusKeyruneToken copy() {
            return new AzoriusKeyruneToken(this);
        }
    }
}
