package ru.otus.timofeev.task7.benchmark;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import ru.otus.timofeev.task7.service.HashPasswordService;
import ru.otus.timofeev.task7.service.MD5Service;
import ru.otus.timofeev.task7.service.SHA256Service;
import ru.otus.timofeev.task7.service.SHA512Service;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime, Mode.Throughput, Mode.SampleTime, Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class HashPasswordServiceBenchmark extends AbstractBenchmark {

    private final String password = "Password123!@#$%";

    HashPasswordService md5Service;
    HashPasswordService sha256Service;
    HashPasswordService sha512Service;

    @Setup(Level.Trial)
    public void setUp() {
        md5Service = new MD5Service();
        sha256Service = new SHA256Service();
        sha512Service = new SHA512Service();
    }

    @Benchmark
    public void md5HashingPasswordBenchmark(Blackhole blackhole) {
        blackhole.consume(md5Service.hashPass(password));
    }

    @Benchmark
    public void sha256HashingPasswordBenchmark(Blackhole blackhole) {
        blackhole.consume(sha256Service.hashPass(password));
    }

    @Benchmark
    public void sha512HashingPasswordBenchmark(Blackhole blackhole) {
        blackhole.consume(sha512Service.hashPass(password));
    }
}