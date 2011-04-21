/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netWork;

/**
 *
 * @author Ahmed Ghanem
 */
public class IpFactory {

    private RealIp realIp;

    public IpFactory() {
        realIp = new RealIp();
    }
    public Ip getSuitableIp() {
        if (realIp.getFlag() == true) {
            return realIp;
        } else {
            return new LocalIp();
        }
    }
}
