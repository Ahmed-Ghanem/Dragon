
package netWork;

/**
 * used as factory to decide return real ip of local ip
 * @author Ahmed Ghanem
 */
public class IpFactory {

    private RealIp realIp;

    public IpFactory() {
        realIp = new RealIp();
    }
    public Ip getSuitableIp() {
        //there is internet access
        if (realIp.getFlag() == true) {
            return realIp;
        } else {
            return new LocalIp();
        }
    }
}
