const { Worker } = require('worker_threads');

function runMultiThreadBenchmark() {
    const numberOfThreads = 10;
    const numberOfIterations = 5;

    const startTime = Date.now();
    const workerPromises = [];

    for (let i = 0; i < numberOfThreads; i++) {
        const workerPromise = new Promise((resolve) => {
            const worker = new Worker('./worker.js', {
                workerData: {
                    threadId: i,
                    numberOfIterations: numberOfIterations / numberOfThreads,
                },
            });

            worker.on('message', resolve);
        });

        workerPromises.push(workerPromise);
    }

    Promise.all(workerPromises)
        .then(() => {
            const endTime = Date.now();
            const executionTime = endTime - startTime;
            console.log(`Execution Time: ${executionTime} ms`);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

runMultiThreadBenchmark();
