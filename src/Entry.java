class Entry
{
    private String name;
    private String street;

    public Entry(String name, String street)
    {
        this.name = name;
        this.street = street;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getName()
    {
        return this.name;
    }

    public String getStreet()
    {
        return this.street;
    }
}
