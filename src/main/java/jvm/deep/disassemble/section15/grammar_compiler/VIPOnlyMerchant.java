package jvm.deep.disassemble.section15.grammar_compiler;

class VIPOnlyMerchant extends Merchant<VIP> {
	@Override
	public double actionPrice(VIP customer) {
		return 0.0d;
	}
}