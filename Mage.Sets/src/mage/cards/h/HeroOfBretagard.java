package mage.cards.h;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.SourceHasAnyCountersCondition;
import mage.abilities.condition.common.SourceMatchesFilterCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.continuous.AddCardSubTypeSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.common.FilterCreaturePermanent;
import mage.game.Game;
import mage.game.events.GameEvent;

import java.util.UUID;
import mage.game.events.ZoneChangeGroupEvent;

/**
 * @author jeffwadsworth
 */
public final class HeroOfBretagard extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();
    private static final FilterCreaturePermanent filter2 = new FilterCreaturePermanent();

    static {
        filter.add(new SourceHasAnyCountersCondition(5));
        filter2.add(new SourceHasAnyCountersCondition(10));
    }

    private static final Condition condition = new SourceMatchesFilterCondition(filter);
    private static final Condition condition2 = new SourceMatchesFilterCondition(filter2);

    public HeroOfBretagard(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // Whenever you exile one or more cards from your hand and/or permanents from the battlefield, put that many +1/+1 counters on Hero of Bretagard.
        this.addAbility(new HeroOfBretagardTriggeredAbility());

        // As long as Hero of Bretagard has five or more counters on it, it has flying and is an Angel in addition to its other types.
        Ability ability = new SimpleStaticAbility(new ConditionalContinuousEffect(
                new GainAbilitySourceEffect(
                        FlyingAbility.getInstance(), Duration.WhileOnBattlefield
                ), condition, "As long as {this} has five or more counters on it, it has flying"
        ));
        ability.addEffect(new ConditionalContinuousEffect(new AddCardSubTypeSourceEffect(
                Duration.WhileOnBattlefield, SubType.ANGEL
        ), condition, "and is an Angel in addition to its other types."));
        this.addAbility(ability);

        // As long as Hero of Bretagard has ten or more counters on it, it has indestructible and is a God in addition to its other types.
        ability = new SimpleStaticAbility(new ConditionalContinuousEffect(new GainAbilitySourceEffect(
                IndestructibleAbility.getInstance(), Duration.WhileOnBattlefield
        ), condition2, "As long as {this} has ten or more counters on it, it has indestructible"));
        ability.addEffect(new ConditionalContinuousEffect(new AddCardSubTypeSourceEffect(
                Duration.WhileOnBattlefield, SubType.GOD
        ), condition2, "and is a God in addition to its other types."));
        this.addAbility(ability);
    }

    private HeroOfBretagard(final HeroOfBretagard card) {
        super(card);
    }

    @Override
    public HeroOfBretagard copy() {
        return new HeroOfBretagard(this);
    }
}

class HeroOfBretagardTriggeredAbility extends TriggeredAbilityImpl {

    HeroOfBretagardTriggeredAbility() {
        super(Zone.BATTLEFIELD, null, false);
    }

    HeroOfBretagardTriggeredAbility(final HeroOfBretagardTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public HeroOfBretagardTriggeredAbility copy() {
        return new HeroOfBretagardTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ZONE_CHANGE_GROUP;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        ZoneChangeGroupEvent zEvent = (ZoneChangeGroupEvent) event;
        if (zEvent != null
                && Zone.HAND == zEvent.getFromZone()
                && Zone.EXILED == zEvent.getToZone()
                && zEvent.getCards() != null) {
            int cardCount = 0;
            cardCount = zEvent.getCards().stream().filter((card)
                    -> (card != null && card.isOwnedBy(getControllerId()))).map((_item)
                    -> 1).reduce(cardCount, Integer::sum);
            if (cardCount == 0) {
                return false;
            }
            this.getEffects().clear();
            this.getEffects().add(new AddCountersSourceEffect(CounterType.P1P1.createInstance(cardCount)));
            return true;
        }
        // the permanent exiled does not have to be controlled/owned by anyone in particular
        if (zEvent != null
                && Zone.BATTLEFIELD == zEvent.getFromZone()
                && Zone.EXILED == zEvent.getToZone()
                && zEvent.getCards() != null) {
            int cardCount = 0;
            cardCount = zEvent.getCards().stream().filter((card)
                    -> (card != null)).map((_item)
                    -> 1).reduce(cardCount, Integer::sum);
            if (cardCount == 0) {
                return false;
            }
            this.getEffects().clear();
            this.getEffects().add(new AddCountersSourceEffect(CounterType.P1P1.createInstance(cardCount)));
            return true;
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever you exile one or more cards from your hand and/or permanents from the battlefield, put that many +1/+1 counters on {this}.";

    }
}
