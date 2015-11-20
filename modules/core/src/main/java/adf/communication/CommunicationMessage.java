package adf.communication;

public class CommunicationMessage
{
    private boolean isRadio;

    public CommunicationMessage(boolean isRadio)
    {
        this.isRadio = isRadio;
    }

    public boolean isRadio()
    {
        return this.isRadio;
    }
}
