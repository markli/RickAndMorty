package common.utils.observer;

import android.os.Message;

/**
 * Observer Pattern Utils Class
 * @author lidongkai
 *
 */
public interface IObserver {

    abstract public void listen(Message inMessage);
}
