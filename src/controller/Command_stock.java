package controller;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.commands.Add_session;
import controller.commands.Command;
import controller.commands.Move;

// that class will get all the command from mycontroller and push it to blockingqueue and at the time will execute the command.
public class Command_stock {
	private BlockingQueue<Command> queue;
	private boolean stop = false;
	

	public Command_stock() {
		queue = new ArrayBlockingQueue<Command>(10);
		this.start();

	}

	// the func push command to queue
	public void insertCommand(Command cmd) {
		try {
			queue.put(cmd);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// the func will execute command at the time he get her turn by thread.
	public void start() {		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!stop) {
					try {
						Command cmd = queue.poll(1, TimeUnit.SECONDS);
						
						if (cmd != null){
							System.out.println(cmd.getClass().getName());
							if (cmd instanceof Move)
							{
								try{
						            Thread.sleep(2000);
						        } catch(InterruptedException e){
						            e.printStackTrace();
						        }
							}
							cmd.execute();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		thread.start();

	}

	public void stop() {

		stop = true;
	}



}
