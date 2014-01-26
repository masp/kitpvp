package masp.plugins.kitpvp.kit.kits.effects;

import java.util.ArrayList;
import java.util.List;

import masp.plugins.kitpvp.kit.kits.Kit;
import masp.plugins.kitpvp.kit.kits.KitInfo;
import masp.plugins.kitpvp.player.KitPlayer;

public class BasicKit implements Kit {

	private KitInfo info;
	private List<KitEffect> effects;

	public BasicKit(KitInfo info, List<KitEffect> effects) {
		this.info = info;

		this.effects = new ArrayList<KitEffect>(effects);
	}

	@Override
	public KitInfo getInfo() {
		return info;
	}

	@Override
	public void apply(KitPlayer player) {
		for (KitEffect effect : effects) {
			effect.apply(player);
		}
	}

	@Override
	public void cease(KitPlayer player) {
		for (KitEffect effect : effects) {
			effect.cease(player);
		}
	}

	@Override
	public void renew(KitPlayer player) {
		System.out.println("Renewed kit: " + getInfo().getDisplayName());
		for (KitEffect effect : effects) {
			if (effect instanceof RenewableEffect) {
				((RenewableEffect) effect).renew(player);
			}
		}
	}

	@Override
	public void suspend(KitPlayer player) {
		System.out.println("Suspended kit: " + getInfo().getDisplayName());
		for (KitEffect effect : effects) {
			if (effect instanceof RenewableEffect) {
				((RenewableEffect) effect).suspend(player);
			}
		}
	}
}
