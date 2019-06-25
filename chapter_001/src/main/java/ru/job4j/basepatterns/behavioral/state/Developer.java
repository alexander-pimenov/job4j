package ru.job4j.basepatterns.behavioral.state;

public class Developer {

    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * метод реализующий изменение активности разработчика
     */
    public void changeActivity() {
        if (activity instanceof Sleeping) { //если activity является япредставителем класса Sleeping, тогда
            setActivity(new Training());    //мы устанавливаем activity Training, т.е. после сна тренируется
        } else if (activity instanceof Training) {
            setActivity(new Coding());
        } else if (activity instanceof Coding) {
            setActivity(new Reading());
        } else if (activity instanceof Reading) {
            setActivity(new Sleeping());
        }
    }
        /**
         * создаем такую обертку
         */
        public void justDoIt(){
        activity.justDoIt();

        }


}
