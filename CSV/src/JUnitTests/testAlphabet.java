package JUnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import View.vwCadastrarQuestoes;

class testAlphabet {

	@Test
	void testA() {
		vwCadastrarQuestoes questoes = new vwCadastrarQuestoes();
		String x = questoes.az(0);
		assertEquals("A", x);
	}

	@Test
	void testM() {
		vwCadastrarQuestoes questoes = new vwCadastrarQuestoes();
		String x = questoes.az(12);
		assertEquals("M", x);
	}

	@Test
	void testZ() {
		vwCadastrarQuestoes questoes = new vwCadastrarQuestoes();
		String x = questoes.az(25);
		assertEquals("Z", x);
	}

}
