package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import View.vwCadastrarQuestoes;

class testAlternativas {

	@Test
	void testMinusculo() {
		vwCadastrarQuestoes questoes = new vwCadastrarQuestoes();
		String x = questoes.tratarA("a) Oi como vai você?");
		assertEquals("Oi como vai você?", x);
	}

	@Test
	void testMaiusculo() {
		vwCadastrarQuestoes questoes = new vwCadastrarQuestoes();
		String x = questoes.tratarA("A) Oi como vai você?");
		assertEquals("Oi como vai você?", x);
	}

}
