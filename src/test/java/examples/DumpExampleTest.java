/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package examples;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class DumpExampleTest extends TestCase {
    public void testDump() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", "Silenthand Olleander");
        data.put("race", "Human");
        data.put("traits", new String[] { "ONE_HAND", "ONE_EYE" });
        Yaml yaml = new Yaml();
        String output = yaml.dump(data);
        System.out.println(output);
        assertTrue(output.contains("name: Silenthand Olleander"));
        assertTrue(output.contains("race: Human"));
        assertTrue(output.contains("traits: [ONE_HAND, ONE_EYE]"));
    }

    public void testDumpWriter() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", "Silenthand Olleander");
        data.put("race", "Human");
        data.put("traits", new String[] { "ONE_HAND", "ONE_EYE" });
        Yaml yaml = new Yaml();
        StringWriter writer = new StringWriter();
        yaml.dump(data, writer);
        System.out.println(writer.toString());
        assertTrue(writer.toString().contains("name: Silenthand Olleander"));
        assertTrue(writer.toString().contains("race: Human"));
        assertTrue(writer.toString().contains("traits: [ONE_HAND, ONE_EYE]"));
    }

    public void testDumpMany() {
        List<Integer> docs = new LinkedList<Integer>();
        for (int i = 1; i < 4; i++) {
            docs.add(i);
        }
        DumperOptions options = new DumperOptions();
        options.setExplicitStart(true);
        Yaml yaml = new Yaml(options);
        System.out.println(yaml.dump(docs));
        System.out.println(yaml.dumpAll(docs.iterator()));
    }

    public void testDumpCustomJavaClass() {
        Hero hero = new Hero("Galain Ysseleg", -3, 2);
        Yaml yaml = new Yaml();
        String output = yaml.dump(hero);
        System.out.println(output);
        assertEquals("!!examples.Hero {hp: -3, name: Galain Ysseleg, sp: 2}\n", output);
    }

    public void testDumperOptions() {
        List<Integer> data = new LinkedList<Integer>();
        for (int i = 0; i < 50; i++) {
            data.add(i);
        }
        Yaml yaml = new Yaml();
        String output = yaml.dump(data);
        System.out.println(output);
        assertTrue(output.contains("[0, 1, 2, 3, 4, 5, 6, 7, 8"));
        //
        DumperOptions options = new DumperOptions();
        options.setWidth(50);
        options.setIndent(4);
        yaml = new Yaml(options);
        output = yaml.dump(data);
        System.out.println(output);
    }

    public void testDumperOptionsCanonical() {
        List<Integer> data = new LinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            data.add(i);
        }
        DumperOptions options = new DumperOptions();
        options.setCanonical(true);
        Yaml yaml = new Yaml(options);
        String output = yaml.dump(data);
        System.out.println(output);
        assertTrue(output.contains("---"));
        assertTrue(output.contains("!!seq ["));
        assertTrue(output.contains("!!int \"3\","));
    }

    public void testDumperOptionsFlowStyle() {
        List<Integer> data = new LinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            data.add(i);
        }
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(Boolean.FALSE);
        Yaml yaml = new Yaml(options);
        String output = yaml.dump(data);
        System.out.println(output);
        assertTrue(output.contains("- 0\n"));
        assertTrue(output.contains("- 1\n"));
        assertTrue(output.contains("- 4\n"));
    }

    public void testDumperOptionsStyle() {
        List<Integer> data = new LinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            data.add(i);
        }
        DumperOptions options = new DumperOptions();
        options.setDefaultStyle('"');
        Yaml yaml = new Yaml(options);
        String output = yaml.dump(data);
        System.out.println(output);
        assertTrue(output.contains("- !!int \"0\""));
        assertTrue(output.contains("- !!int \"1\""));
        assertTrue(output.contains("- !!int \"4\""));
    }

}
