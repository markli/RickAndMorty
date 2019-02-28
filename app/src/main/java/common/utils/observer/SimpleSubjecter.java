/**
 * 
 */
package common.utils.observer;

import android.os.Message;

/**
 * @author lidongkai
 *
 */
public abstract class SimpleSubjecter implements ISubject {

    /* (non-Javadoc)
     * @see common.utils.observer.ISubject#attach(common.utils.observer.IObserver)
     */
    @Override
    public boolean attach(IObserver inObserver) {
        return ObserverAdapter.getInstance().register(this, inObserver);
    }

    /* (non-Javadoc)
     * @see common.utils.observer.ISubject#detach(common.utils.observer.IObserver)
     */
    @Override
    public boolean detach(IObserver inObserver) {
        return ObserverAdapter.getInstance().unregister(this, inObserver);
    }

    /* (non-Javadoc)
     * @see common.utils.observer.ISubject#notify(android.os.Message, int)
     */
    @Override
    public void notify(Message inMessage, int... flag) {
        ObserverAdapter.getInstance().notify(this, inMessage, flag);
    }

}
