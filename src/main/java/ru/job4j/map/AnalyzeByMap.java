package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {

    public static double averageScore(List<Pupil> pupils) {
        double avg = 0;
        int count = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                avg += subject.score();
                count++;
            }
        }
        return avg / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double avg = 0;
            for (Subject subject : pupil.subjects()) {
                avg += subject.score();
            }
            avg /= pupil.subjects().size();
            labels.add(new Label(pupil.name(), avg));
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Double> subjectAverages = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjectAverages.putIfAbsent(subject.name(), 0D);
                subjectAverages.replace(subject.name(), subjectAverages.get(subject.name()) + subject.score());
            }
        }
        List<Label> labels = new ArrayList<>();
        for (String subjectName : subjectAverages.keySet()) {
            double avg = subjectAverages.get(subjectName) / pupils.size();
            labels.add(new Label(subjectName, avg));
        }
        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        Map<String, Integer> pupilTotals = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                pupilTotals.putIfAbsent(pupil.name(), 0);
                pupilTotals.replace(pupil.name(), pupilTotals.get(pupil.name()) + subject.score());
            }
        }
        List<Label> labels = new ArrayList<>();
        for (Map.Entry<String, Integer> pupilTotal : pupilTotals.entrySet()) {
            labels.add(new Label(pupilTotal.getKey(), pupilTotal.getValue()));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        HashMap<String, Integer> subjectTotals = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjectTotals.putIfAbsent(subject.name(), 0);
                subjectTotals.replace(subject.name(), subjectTotals.get(subject.name()) + subject.score());
            }
        }
        List<Label> labels = new ArrayList<>();
        for (Map.Entry<String, Integer> subjectTotal : subjectTotals.entrySet()) {
            labels.add(new Label(subjectTotal.getKey(), subjectTotal.getValue()));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }
}
