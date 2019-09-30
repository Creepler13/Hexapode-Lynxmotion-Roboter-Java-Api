package api.advanced;

import java.util.ArrayList;

import api.basic.Hexapode;

public class Collection extends Hexapode {

	ArrayList<Bundle> Bundles = new ArrayList<Bundle>();

	public Collection() {
	}

	public void exec() {
		for (int i = 0; i < Bundles.size(); i++) {
			serialcommand(Bundles.get(i).command + " T" + Bundles.get(i).time);
		}
	}

	public void add(Bundle bundle) {
		Bundles.add(bundle);
	}

	public void add(Collection collection) {
		Bundles.addAll(collection.Bundles);
	}

}
