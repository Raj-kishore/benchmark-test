import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MultiThreadBenchmark {

  public static void main(String[] args) throws Exception {
    int numberOfThreads = 10;
    int numberOfIterations = 5;
    
    ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
    
    long startTime = System.currentTimeMillis();
    Future<?>[] futures = new Future<?>[numberOfThreads];
    
    for (int i = 0; i < numberOfThreads; i++) {
      final int threadId = i;
      futures[i] = executor.submit(new Callable<Void>() {
        public Void call() {
          performComputation(threadId, numberOfIterations / numberOfThreads);
          return null;
        }
      });
    }
    
    for (Future<?> future : futures) {
      future.get();
    }
    
    executor.shutdown();
    
    long endTime = System.currentTimeMillis();
    long executionTime = endTime - startTime;
    
    System.out.println("Execution Time: " + executionTime + " ms");
  }
  
  private static void performComputation(int threadId, int numberOfIterations) {
            long result = 0;
  for (int i = 0; i < numberOfIterations; i++) {
    // Perform some heavy computation
    result += i * i;
  }
  System.out.println("Thread " + threadId + " Result: " + result); 
  }
}
