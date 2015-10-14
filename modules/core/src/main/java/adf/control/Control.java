package adf.control;

/**
 * Created by takamin on 10/13/15.
 */
public abstract class Control
{
	private Control parentControl;

	public Control(Control parent)
	{
		this.parentControl = parent;
	}

	public Control()
	{
		this(null);
	}

	abstract public void initialize();
	abstract public void resume();
	abstract public void preparate();
	abstract public void think();

	public Control getParentControl()
	{
		return parentControl;
	}
}
