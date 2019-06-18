package test.utils;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import ch.bytecrowd.voci.util.HashUtil;

public class HashUtilTest {

	@Test
	public void test01() {
		String hashMD5 = HashUtil.hashMD5("admin");
		assertThat(hashMD5, is("21232f297a57a5a743894a0e4a801fc3"));
	}

	@Test
	public void test02() {
		String hashMD5 = HashUtil.hashMD5("fdsa8d09asf8dsa fd8sa0 fdsa/(&&/(:_");
		assertThat(hashMD5, is("a97643c0ada2378c0ab2b298769c7fe6"));
	}

	@Test
	public void test03() {
		String hashMD5 = HashUtil.hashMD5("uoaifsduoPUIOODUSAOI  dsoiauDOAISDUA/)(/=&SA/(D&A/(&DSA/(&(dsa");
		assertThat(hashMD5, is("bfe4f05a5142103c6f43c33ce29b0bd6"));
	}

	@Test
	public void test04() {
		String hashMD5 = HashUtil.hashMD5("()=D(SA=)D(SAD 890S D(=) DSA(D=)AS(SA/D()SA&D//(%&/AS%&ASçDAS");
		assertThat(hashMD5, is("7c8fe95dc82d3bbb0cddfc94648c2e0d"));
	}

}
