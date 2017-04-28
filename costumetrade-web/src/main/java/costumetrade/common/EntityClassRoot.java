package costumetrade.common;

import java.util.List;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * MyBatis Generator plugin to give query Example classes a common root class.
 *
 * This plugin accepts one properties:
 * <ul>
 * <li><tt>exampleRootClass</tt> (required) the fully qualified class name for
 * the base class</li> For example, to use foo.bar.ExampleBase as a base class:
 * <code>
 * <generatorConfiguration>
 	<classPathEntry location="/path/to/MyBatisExampleRootPlugin.jar" />

    <context id="context1" >
      <plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin" />
      <plugin type="org.mybatis.generator.plugins.SerializablePlugin" />
      <plugin type="costumetrade.common.EntityClassRoot">
         <property name="superClass" value="costumetrade.common.Entity" />
      </plugin>
   </code>
 * 
 * @author dante
 */
public class EntityClassRoot extends PluginAdapter {
	private String baseClassName;

	public EntityClassRoot() {
	}

	@Override
	public boolean validate(List<String> warnings)
	// --------------------------------------------
	{
		baseClassName = properties.getProperty("superClass");
		boolean isValid = stringHasValue(baseClassName);
		if (!isValid)
			warnings.add("ExampleClassRoot plugin: superClass property not found");
		return isValid;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (baseClassName != null) {
			FullyQualifiedJavaType superClass = new FullyQualifiedJavaType(baseClassName);
			topLevelClass.addImportedType(superClass);
			topLevelClass.setSuperClass(superClass);
			
			Field field = new Field();
            field.setFinal(true);
            field.setInitializationString("1L"); //$NON-NLS-1$
            field.setName("serialVersionUID"); //$NON-NLS-1$
            field.setStatic(true);
            field.setType(new FullyQualifiedJavaType("long")); //$NON-NLS-1$
            field.setVisibility(JavaVisibility.PRIVATE);
            context.getCommentGenerator().addFieldComment(field, introspectedTable);

            topLevelClass.addField(field);
		}
		return true;
	}
	
}
